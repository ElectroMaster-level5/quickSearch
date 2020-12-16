# quickSearch
little tool for searching a keyword and get its locations. 

### Usage:

```
Enter base directory (e.g. /home/exia/project/src): 
/home/exia/workspace/learn/quicksearch
Enter keyword (e.g. volatile): 
executor.submit
```
then you can get the output like below:
```bash
/home/exia/workspace/learn/quicksearch/src/main/java/com/randomlink/App.java [line: 34]:             executor.submit(new FileEnumerationTask(queue, new File(directory)));
/home/exia/workspace/learn/quicksearch/src/main/java/com/randomlink/App.java [line: 38]:             executor.submit(new SearchTask(queue, keyword));
```