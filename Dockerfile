FROM maven as build

LABEL authors="tonybcod1ng"

COPY . .

RUN mvn clean package

ENTRYPOINT ["top", "-b"]