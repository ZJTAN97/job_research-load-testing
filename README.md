# Taurus installtion using Docker image

```

docker run -it --rm -v C:/Users/tan.zj/Documents/GitHub/job_research-load-testing-tools/taurus/taurus_scripts:/bzt-configs blazemeter/taurus sample.yaml


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
