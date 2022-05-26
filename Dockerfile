FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22 as builder
WORKDIR /app

# For SDKMAN to work we need unzip & zip
RUN yum install -y unzip zip

RUN \
    # Install SDKMAN
    curl -s "https://get.sdkman.io" | bash; \
    source "$HOME/.sdkman/bin/sdkman-init.sh"; \
    sdk install maven; \
    # Install GraalVM Native Image
    gu install native-image;

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn --version

RUN native-image --version

COPY . .

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -B clean package -Pnative --no-transfer-progress -DskipTests

FROM oraclelinux:7-slim
WORKDIR /app

COPY --from=builder /app/target/funny-converter-be ./funny-converter-be

EXPOSE 8080
CMD ["sh", "-c", "/app/funny-converter-be"]

