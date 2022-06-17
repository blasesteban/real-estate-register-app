docker build . -t real-estate-register
docker run --rm -it --name real-estate-register -p8080:8080 real-estate-register
