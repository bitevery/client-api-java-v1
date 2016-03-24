# ApiCode Endpoint Instruction
The apiCode endPoint operates only one functionaliy: retrieve or create an api code for the client.

## Input Parameter
* username: A username of bitevery account.
* password: The password for the user account

## Output Return
* ApiCode: A string of 32 digits hash code

## Example
``` java
  import com.bitevery.api.ApiCode;
  
  String apicode = ApiCode.getApiCode(&username, &password);
  System.out.println(apiCode);
```
```
>>> 0123456789abcdefghijklmnopqrstuvw
```
