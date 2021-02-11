## Task
You have a big text gzipped text file that may not fit in RAM, one string in each line. For example,  
S1  
S2  
S4  
S3  
...  
There’s an HTTP service that assigns numbers (integers) to each of the input strings that your
application should call. For example, for http://localhost/?s=S1 might return JSON {"s": "S1", "value": 10}. It
may also return an error for some time or always for all strings or for some strings. If the service returns a
successful response, it does so deterministically (same response for same request). It may be that it fails
for some time but later responds successfully. It supports high load, but it may fail in various ways or be
inaccessible.  

Write a Java application to assign a number to each string and write pairs (string, number) to another file
or a database. The URL of the HTTP service should be configurable. If you choose to write to a text file,
TSV might be used, for example,
S1 10
S2 45
S3 60
You should try to assign as many numbers as possible. It should be possible to run your application with
java -jar your-application.jar. You should also implement mock number assigner service. In
case of ambiguities, decide how to resolve them on your own.

## Solution
