# Taurus installation using Docker image

```

docker run -it --rm -v C:/Users/tan.zj/Documents/GitHub/job_research-load-testing-tools/taurus/taurus_scripts:/bzt-configs blazemeter/taurus multi_scenario_req.yaml

docker run -it --rm -v C:/Users/tan.zj/Downloads/taurus_resources:/bzt-configs blazemeter/taurus 


  - docker run -it --rm -v `pwd`:/TaurusLoadTesting blazemeter/taurus /TaurusLoadTesting/profile/sanity.yml /TaurusLoadTesting/common/scenarios.yml /TaurusLoadTesting/variable/development.yml


```

<br>

## Taurus installation with Python

```
# Create Virtual Environment
python -m venv venv


# Activate Virtual Environment
source venv/Scripts/activate

pip install --upgrade wheel setuptools Cython

pip install bzt
pip install --upgrade bzt


```
