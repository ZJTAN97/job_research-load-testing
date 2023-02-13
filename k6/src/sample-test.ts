import { group } from "k6";
import http from "k6/http";

export const options = {
  scenarios: {
    shared_iter_scenario: {
      executor: "shared-iterations",
      vus: 10,
      iterations: 100,
      startTime: "0s",
    },
    per_vu_scenario: {
      executor: "per-vu-iterations",
      vus: 10,
      iterations: 10,
      startTime: "10s",
    },
  },
};

export default function () {
  group("Visting multi select page", function () {
    http.get("https://mantine.dev/core/multi-select/");
  });

  group("Visiting native select page", function () {
    http.get("https://mantine.dev/core/native-select/");
  });
}
