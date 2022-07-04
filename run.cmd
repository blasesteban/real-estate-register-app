docker build . -t real-estate-register
docker run --rm -it --name real-estate-register -e USERNAME=sa -e PASSWORD=password -p8080:8080 real-estate-register
