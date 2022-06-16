docker run -it --rm --name realestateregister-h2 -p 8082:8082 buildo/h2database
docker build . -t realestateregisterapp
docker run --rm -it --name realestateregisterapp -p 8080:8080 realestateregisterapp