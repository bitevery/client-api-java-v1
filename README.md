# client-api-java-v1
Official client API to interact with www.bitevery.com.

## Installation
* Download bitevery_api_v1.jar file in this repository
* Add the jar file into the build path of desired project
* import com.bitevery.api.* in the target java file

## File Structure
The package is called com.bitevery.api. Two files in the package provide services as follow:
* tip ([docs](Documentation/tip.md))
* apiCode ([docs](Documentation/apiCode.md))

## API Code Required
In order to hide the user information in the API calls as much as possible, bitevery requires an API code to access all the service endpoints. API is available by using apiCode module.
