FROM node:lts-alpine

COPY ["package.json", "package-lock.json*", "./"]

RUN npm i @vue/cli-service -g
RUN npm install --production

COPY . .

CMD [ "npm", "run", "serve" ]