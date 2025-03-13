## Test AWS S3 SDK vers Clever Cloud

Les dernières versions de la librairie (2.30.x) provoquent une exception lorsque l'on upload un fichier vers Cellar. La lecture ne pose pas de soucis.

```
Listing files from CC S3...
 - my_file_2_29_52.txt
Done.
Uploading file to CC S3...
Error : XAmzContentSHA256Mismatch
software.amazon.awssdk.services.s3.model.S3Exception:  (Service: S3, Status Code: 400, Request ID: tx000002a5f8630214808a2-0067d2e0e1-31f40413-default) (SDK Attempt Count: 1)
at software.amazon.awssdk.services.s3.model.S3Exception$BuilderImpl.build(S3Exception.java:113)
at software.amazon.awssdk.services.s3.model.S3Exception$BuilderImpl.build(S3Exception.java:61)
```

La dernière version pleinement fonctionnelle est la 2.29.52.

Pour tester, il suffit de mettre les credentials vers un bucket créé avec Cellar dans la classe main.
Il est aussi possible de mettre AWS SDK en debug dans log4j2.xml pour voir la tentative de signature...

```
2025-03-13 15:29:32 [main] DEBUG software.amazon.awssdk.http.auth.aws.internal.signer.DefaultV4RequestSigner:85 - AWS4 String to sign: AWS4-HMAC-SHA256
20250313T142932Z
20250313/eu-west-1/s3/aws4_request
3cb02897da241f6ed12e2ac1992a331332c4d0a380855d5df087b2d09b09d9b9
```
