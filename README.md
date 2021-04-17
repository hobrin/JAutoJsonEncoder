# JAutoJsonEncoder
Easy java program to automatically generate java code to initiate a JsonObject using Gson. My code uses java and Gson too. Make sure to add Gson as a library: https://github.com/google/gson

# Use
You could download a compiled jar here, with the instructions on how to run it.
https://github.com/hobrin/JAutoJsonEncoder/releases/tag/1.0

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
# License
Make sure to credit me, and keep the license. For further information look at the LICENSE file.

# Improvements
Let me know if you would want a feature that automatically tabs the lines, because now the result is just a mess. Also, code could be made a lot easier:
```
JsonPrimitive age = new JsonPrimitive(10);
json.add("age", age);
```
this could be shortened too:
```
json.addProperty("age", 10);
```
which makes a lot more sense, but that would be kinda tricky to implement.
