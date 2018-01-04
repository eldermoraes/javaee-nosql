# Build
mvn clean package && docker build -t br.com.eldermoraes/careerbuddy .

# RUN

docker rm -f careerbuddy || true && docker run -d -p 8080:8080 -p 4848:4848 --name careerbuddy br.com.eldermoraes/careerbuddy 