# Running K6 with Docker

```
# When using the `k6` docker image, you can't just give the script name since
# the script file will not be available to the container as it runs. Instead
# you must tell k6 to read `stdin` by passing the file name as `-`. Then you
# pipe the actual file into the container with `<` or equivalent. This will
# cause the file to be redirected into the container and be read by k6.

docker run --rm -i grafana/k6 run - <script.js

```

<br>

# The init context and default function

- For a test to run, you need to have init code, which prepares the test, and VU code, which makes requests.
- Code in the init context defines functions and configures the test options (like duration).
- Every test also has a default function. This function defines the entry point for your VUs.

<br>

# Using options

- Instead of using flags, define in the script as such.

```
import http from 'k6/http';
import { sleep } from 'k6';
export const options = {
  vus: 10,
  duration: '30s',
};
export default function () {
  http.get('http://test.k6.io');
  sleep(1);
}

```

<br>

# Stages: ramping up/down Virtual Users (VUs)

```

import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '1m30s', target: 10 },
    { duration: '20s', target: 0 },
  ],
};

export default function () {
  const res = http.get('https://httpbin.test.k6.io/');
  check(res, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
}

```

<br>

# Http Requests

A simple GET request example

```
import http from 'k6/http';

export default function () {
  http.get('http://test.k6.io');
}

```

A simple POST request example

```
import http from 'k6/http';

export default function () {
  const url = 'http://test.k6.io/login';
  const payload = JSON.stringify({
    email: 'aaa',
    password: 'bbb',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
}

```

URL Grouping example

```
import http from 'k6/http';

export default function () {
  for (let id = 1; id <= 100; id++) {
    http.get(`http://example.com/posts/${id}`);
  }
}
// tags.name=\"http://example.com/posts/1\",
// tags.name=\"http://example.com/posts/2\",

```

<br>

# Metrics

- `Counter` sum values
- `Gauges` track the smallest, largest and latest values
- `Rates` track how frequently a non-zero value occurs
- `Trends` calculate the statistics for multiple values (like mean or mode)

Accessing HTTP timings from a script

- use `Response.timings` object to retrieve the time spent on various phases in milliseconds.

```
import http from 'k6/http';

export default function () {
  const res = http.get('http://httpbin.test.k6.io');
  console.log('Response time was ' + String(res.timings.duration) + ' ms');
}

```

Custom metrics

```

import http from 'k6/http';
import { Trend } from 'k6/metrics';

const myTrend = new Trend('waiting_time');

export default function () {
  const r = http.get('https://httpbin.test.k6.io');
  myTrend.add(r.timings.waiting);
  console.log(myTrend.name); // waiting_time
}

```

<br>

# Checks

- Checks are similar to what many testing frameworks call an assert, but failed checks do not cause the test to abort or finish with a failed status.
- k6 keeps track of the rate of failed checks as the test continues to run.

Check HTTP response code

```

import { check } from 'k6';
import http from 'k6/http';

export default function () {
  const res = http.get('http://test.k6.io/');
  check(res, {
    'is status 200': (r) => r.status === 200,
  });
}

```

Check for content in response body

```
import { check } from 'k6';
import http from 'k6/http';

export default function () {
  const res = http.get('http://test.k6.io/');
  check(res, {
    'verify homepage text': (r) =>
      r.body.includes('Collection of simple web-pages suitable for load testing'),
    'body size is 11,105 bytes': (r) => r.body.length == 11105,
  });
}


```

<br>

# Thresholds

- This is where it is better than JMeter, we can create thresholds with k6 for any combination such as

  - Less than 1% of requests returns an error
  - 95% of requests have a response time < 200ms
  - 99% of requests have a response time below < 400ms

- Thresholds are essential for load-testing automation
  - Give your test a threshold
  - Automate your execution
  - Set up alerts for test failures

Example: HTTP errors and response duration

```
import http from 'k6/http';

export const options = {
  thresholds: {
    http_req_failed: ['rate<0.01'], // http errors should be less than 1%
    http_req_duration: ['p(95)<200'], // 95% of requests should be below 200ms
  },
};

export default function () {
  http.get('https://test-api.k6.io/public/crocodiles/1/');
}


```

<br>

# Scenarios

- Scenarios configure how VUs and iteration schedules in granular detail
- Ability to model diverse workloads and or traffic patterns in load tests.
