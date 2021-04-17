# JAutoJsonEncoder
Easy java program to automatically generate java code to initiate a JsonObject using Gson. My code uses java and Gson too. Make sure to add Gson as a library: https://github.com/google/gson

# Example
```
{"age":10,"name":"smith","parent":{"lastName":"Smith"},"videoGames":["Tetris","Pong"]}
```
will result in:
```
JsonObject json = new JsonObject();

JsonPrimitive age = new JsonPrimitive(10);
json.add("age", age);

JsonPrimitive name = new JsonPrimitive("smith");
json.add("name", name);

JsonObject parent = new JsonObject();

JsonPrimitive lastName = new JsonPrimitive("Smith");
parent.add("lastName", lastName);


json.add("parent", parent);

JsonArray videoGames = new JsonArray();

JsonPrimitive videoGames_0 = new JsonPrimitive("Tetris");
videoGames.add(videoGames_0);
JsonPrimitive videoGames_1 = new JsonPrimitive("Pong");
videoGames.add(videoGames_1);

json.add("videoGames", videoGames);
```
#License
Make sure to credit me, and keep the license. For further information look at the LICENSE file.
