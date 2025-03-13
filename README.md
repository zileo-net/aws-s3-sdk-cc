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
