# WeCast 2.0 Core

The SDK provides collection of ObjectMapper data models as well Realm object models. In general, SDK simplifies the way how network requests are made and simplifies managment of networking and parsed data usage for front-end application.
Simply by using protocol-oriented programming. SDK performs JSON parsing and network requests and gives data parsed and ready to cache or use instantenously on frontend-app.

SDK contains following submodules:

## API (Retrofit 2)
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization. Typically for JSON you use Gson, but you can add custom converters to process XML or other protocols. Retrofit uses the OkHttp library for HTTP requests.

#### Service
In service classes we define the REST API for Retrofit.
Every method of an interface represents one possible API call. It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL. The return value wraps the response in a Call object with the type of the expected result.

## DB (Realm)

#### Entity
This is nothing but a model class annotated with @Entity where all the variable will becomes column name for the table and name of the model class becomes name of the table. The name of the class is the table name and the variables are the columns of the table.

#### Dao
DAO class is an interface which acts is an intermediary between the user and the database. All the operation to be performed on a table has to be defined here. We define the list of operation that we would like to perform on table.

#### Pref (Shared preferences)
For data that does not require structure we store it in Shared Preferences. The SharedPreferences APIs allow us to read and write persistent key-value pairs of primitive data types: booleans, floats, ints, longs, and strings.

# Repository
A Repository mediates between the domain and data mapping layers, acting like an in-memory domain object collection. We access the database class and the DAO class from the repository and perform list of operations such as insert, update, delete, get.