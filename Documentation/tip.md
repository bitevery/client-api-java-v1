# Tip Endpoint Instruction
The tip endpoint supports tip link creation for single or multiple receiver(s) in three formats: URL, HTML and QRCODE

## Input Parameter
* ApiCode (required): the api code of the user ([apiCode docs](apiCode.md))
* Format (optional): URL, HTML and QRCODE. Default is URL.
* Locale (optional): effective only if the format is QRCODE. Default is 'en-us'. The other option is 'zh'.
* Distribution (optional): key-value pair for multiple receivers. See example below.
  * receiver: the receiver's email address. 
  * percentage: the percentage of distribution for the receiver
  * receriver-percentage have to pair up like this: receiver1=abc@123.com, percent1=50, receiver=def@456.com, percent2=50
  * the sum of all percentage must be 100.

## Output Return
A string of tip link in desired format. If the format is QRCODE, the return is an address to fetch the image.

## example
### Single Receiver
``` java
  import com.bitevery.api.*;
  Tip tip_Single = new Tip();
  tip_Single.getTipLink("0123456789abcdefghijklmnopqrstuvw");
```
```
>>> https://www.bitevery.com/tip.php?tid=9999999
```
#### HTML format
``` java
  tip_Single.setFormat("HTML");
  tip_Single.getTipLink("0123456789abcdefghijklmnopqrstuvw");
```
```
>>> <div id="bitEveryTip2345d"></div></script><script src="https://www.bitevery.com/be-js/tip_button.js"></script><script>tip_btn_setup(6,"6303d",1,30,30);</script>
```
#### QRCODE format
``` java
  tip_Single.setFormat("QRCODE");
  tip_Single.getTipLink("0123456789abcdefghijklmnopqrstuvw");
```
```
>>> https://www.bitevery.com/restAPI/V1.0/qrcode.php?username=bitevery&tid=9999999&locale=en-us
```
Go to the link above, an image of the qrcode will show up
![QRCODE](image/qr_code_en_us.png)
#### QRCODE format with locale
``` java
  tip_Single.setFormat("QRCODE");
  tip_Single.setLocale("zh");
  tip_Single.getTipLink("0123456789abcdefghijklmnopqrstuvw");
```
```
>>> https://www.bitevery.com/restAPI/V1.0/qrcode.php?username=bitevery&tid=9999999&locale=zh
```
Go to the link above, an image of the qrcode will show up
![QRCODE](image/qr_code_zh.png)

### Multiple Receivers
``` java
  import com.bitevery.api.*;
  tip_Multiple = new Tip();
  Map<String, String> distribution = new HashMap<String, String>();
  distribution.put("receiver1", "abc@123.com");
  distribution.put("receiver2", "def@245.com");
  distribution.put("percent1", "50");
  distribution.put("percent2", "50");
  tip_Multiple.getTipLink("0123456789abcdefghijklmnopqrstuvw", distribution);
```
```
>>> https://www.bitevery.com/tip.php?tid=88888888
```
#### HTML Format and QRCODE Format
See the examples above for single receiver.
