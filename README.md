# OOP_Assignment_2
### 3rd assignment of the course <I>Object Oriented Programming</I>.
### Computer Sience B.Sc. Ariel University.
![Ariel University Logo](https://user-images.githubusercontent.com/102155998/212206551-8d583ab5-401e-48ee-99fa-aba54551930a.png)

## Authors

- [@Aviv Turgeman](https://github.com/avivTurgeman)
- [@Yasmin Isakov](https://github.com/Yasmin0610)

## Brief Packages Description
  the packeges described below are in the `src` folder

- ### Ex2_1:
  expresses the time saving of using a Thread and a ThreadPool.

- ### Ex2_2:
  extends the defult ThreadPoolExecutor so it will considerd a priority for tasks in his Queue.
  

# Ex2_1
<ins>Files included:</ins> <br>
- #### Ex2_1.java:
    Where all the code is written.

- #### Ex2_1_Test.java:
    Test class to check code correctness.

- #### main.java:
    Class to see the Runtime differences using Thread and ThreadPool.

- #### Ex2_1_UML:
    Picture of the Class Diagram of this package.
    
    
## Ex2_1.java
<ins>This class have the following methods:</ins> <br>
- #### `createTextFiles(int n, int seed, int bound)` <br>
    creating <I><B>n</B></I> text files where each file have maximum of <I><B>bound</B></I> lines.<br>
    each run is different according to the <I><B>seed</B></I>.<br>
    This method returns a String[n] of the file names.

- #### `getNumOfLines(String[] fileNames)` <br>
    This method returns the number of lines of all the files int the String[n] combine, <br>
    where <I><B>n</B></I> is the number of files. <br>
    This is a generic solution (each file at a time).

- #### `getNumOfLinesThreads(String[] fileNames)` <br>
    This method returns the number of lines of all the files int the String[n] combine, <br>
    where <I><B>n</B></I> is the number of files. <br>
    This solution works with one Thread besides the Main Thread.

- #### `getNumOfLinesThreadPool(String[] fileNames)` <br>
    This method returns the number of lines of all the files int the String[n] combine, <br>
    where <I><B>n</B></I> is the number of files. <br>
    This solution works with a ThreadPool.

- #### `deleteTextFiles(int n)` <br>
    This method delete the <I><B>n</B></I> files we created with `createTextFiles(int n, int seed, int bound)`. <br>
    <ins><B>NOTE!</B></ins> int <I><B>n</B></I> needs to be provided manualy, according
    to <I><B>n</B></I> value in `createTextFiles(int n, int seed, int bound)`.


## Ex2_1_Test.java
<ins>This class have the following Test method:</ins> <br>
- #### `main()` <br>
    This Test method creats <I><B>n</B></I> files using `createTextFiles(int n, int seed, int bound)` <br>
    and saves the names of the files it created in a String[n] array called fileNames. <br>
    Then the Test method checks if the return value of <br>
    `getNumOfLines(fileNames)` , <br>
    `getNumOfLinesThreads(fileNames)` , <br>
    `getNumOfLinesThreadPool(fileNames)` is the same.

![Ex2_1_Test](https://user-images.githubusercontent.com/102155998/212220607-c53ad797-fcd6-4f50-a769-2af65aea9b87.png)


## main.java
<ins>This class have the following method:</ins> <br>
- #### `public static void main(String[] args)` <br>
    <ins>We have 8 variables:</ins>
    - long <I><B>start</B></I> - starting the time counting.
    - long <I><B>end</B></I> - ending the time counting.
    - int <I><B>filesAmount</B></I> - value of <I><B>n</B></I> in `createTextFiles(int n, int seed, int bound)`.
    - int <I><B>seed</B></I> - value of <I><B>seed</B></I> in `createTextFiles(int n, int seed, int bound)`.
    - int <I><B>bound</B></I> - value of <I><B>bound</B></I> in `createTextFiles(int n, int seed, int bound)`.
    - int <I><B>noThreads</B></I> - the time it took to count the lines of the files generically.
    - int <I><B>oneThread</B></I> - the time it took to count the lines of the files with one Thread.
    - int <I><B>ThreadPool</B></I> - the time it took to count the lines of the files with a ThreadPool.
    
    first we creats <I><B>filesAmount</B></I> files using `createTextFiles(int n, int seed, int bound)` <br>
    and saves the names of the files it created in a String[n] <I><B>fileNames</B></I>. <br>
    Then we save the times it took to generate an answer with each method. <br>
    <I><B>noThreads</B></I> = `getNumOfLines(fileNames)` , <br>
    <I><B>oneThread</B></I> = `getNumOfLinesThreads(fileNames)` , <br>
    <I><B>ThreadPool</B></I> = `getNumOfLinesThreadPool(fileNames)`. <br>
    and compares the RunTime differences. <br>
    <ins><B>output:</B></ins> <br>
    first of all we prints the amount of files we created, then we print the time it took for each method <br>
    and at the end, we print the amount of lines that each method counted.
    
    ![Ex2_1_main](https://user-images.githubusercontent.com/102155998/212226345-ece1ce30-f085-4f1c-a4e4-e9e8e31a60ed.png)
    

## Ex2_1_UML

![Ex2_1_UML](https://user-images.githubusercontent.com/102155998/212226584-96ca6c82-d70e-4b6b-8aae-491830014518.png)



# Ex2_2
<ins>Files included:</ins>
- #### Task.java:
    The Task class represents a task that can be executed by the program.

- #### TaskType.java:
    An enum to prioritize the tasks.

- #### Adapter.java:
    The Adapter class is an implementation of the FutureTask class and Comparable interface.
    
- #### CustomExecutor.java:
    CustomExecutor is a class that extends ThreadPoolExecutor and adds functionality for handling tasks with different priorities.

- #### Ex2_2_Tests.java:
    Test class to check correctness of our code.

- #### Ex2_2_UML.png:
    Picture of the Class Diagram of this package.
    

## Task.java
  The Task class represents a task that can be executed by the program. It is implemented as a Callable object <br>
  and can be used in the Executor framework or any other framework that uses Callable objects. <br>
  The Task class also implements the Comparable interface, which allows tasks to be compared and sorted by their priority. <br>

  <ins>This class have 3 private final variables:</ins>
  - private final TaskType <I><B>taskType</I></B> - The task's type, represented by an enumeration. <br>
  - private final Callable<V> <I><B>callable</I></B> - The callable object that the task wraps. <br>
  - private final int <I><B>priority</I></B> - The task's priority, determined by its task type. <br>

  <ins>This class have the following methods:</ins> <br>
  - #### `Task(Callable<V> callable, TaskType taskType)` <br>
      A private Constructor for our use only. <br>
      Creates a new task with the given callable object and task type.
  
  - #### `createTask(Callable<V> callable, TaskType taskType)` <br>
      Creates a new task with the given callable object and task type. <br>
      This method returns a new task with the given callable object and task type.
  
  - #### `createTask(Callable<V> callable)` <br>
      Creates a new task with the given callable object and default task type. <br>
      This method returns a new task with the given callable object and task type.
  
  - #### `getTaskType()` <br>
      Return The task's type.
  
  - #### `compareTo(Task task)` <br>
      Compares this task to another task based on their priority. <br>
      This method returns a negative integer, zero, or a positive integer <br>
      as this task's priority is less than, equal to, or greater than the specified task's priority.
  
  - #### `call()` <br>
    Executes the task's callable object and returns the result. <br>
    This method returns The result of the callable's execution, <br>
    and throws any exception that may be thrown by the callable's execution.
  
  
## TaskType.java
  An attached enum we received with the assignment. <br>
  This enum is given to any task and it represent the priority of the task <br>
  with Integers 1-3 where 1 is the highest priority.
  

## Adapter.java
  The Adapter class is an implementation of the FutureTask class and Comparable interface. <br>
  It is used to encapsulate a Callable and a priority value, <br>
  which can be used to compare and order instances of this class.
  
  <ins>This class have 1 private final variable:</ins>
  - private final int <I><B>priority</I></B> - The task's priority, determined by its task type. <br>
  
  <ins>This class have the following methods:</ins> <br>
  - #### `Adapter(Callable<V> callable, int priority)` <br>
     Constructs a new Adapter instance, encapsulating the provided Callable and priority value.
  
  - #### `compareTo(Adapter taskFuture)` <br>
      Compares this Adapter instance with the specified Adapter instance which is given. <br>
      This method return a negative integer, zero, or a positive integer <br>
      as this object's priority is less than, equal to, or greater than the specified object's priority.
  
  - #### `getPriority()` <br>
      This method returns the priority value of this Adapter instance.
  

## CustomExecutor.java
  CustomExecutor is a class that extends ThreadPoolExecutor and adds functionality for handling tasks with different priorities. <br>
  It uses a PriorityBlockingQueue to store the tasks and the priority of each task is determined by its TaskType. <br>
  The maximum pool size of the thread pool is set to the number of available processors minus one, and the core pool size <br>
  is set to half the number of available processors. <br>
  In addition to that, this class obtains an array that holds the number of tasks of each priority.<br>
  
  <ins>This class have 4 private final variables:</ins>
  - private final int[] <I><B>QueuePriorityArr</B></I> - counts the tasks in the queue with consideration of priority.
  - private static final int <I><B>CorePoolSize</B></I> - the available Processors divided by 2.
  - private static final int <I><B>MaximumPoolSize</B></I> - the available Processors minus 1.
  - private static final long <I><B>keepAliveTime</B></I> - the maximum time that excess 
    idle threads will wait for new tasks before terminating in milliseconds.
  
  <ins>This class have the following methods:</ins> <br>
  - #### `CustomExecutor()` <br>
      Constructs a CustomExecutor with the default core pool size, maximum pool size, and keep alive time. <br>
      The thread pool uses a PriorityBlockingQueue to hold the tasks.
  
  - #### `submit(src.Ex2_2.Task<V> task)` <br>
      Submits a task to the thread pool.
  
  - #### `submit(Callable<V> callable, TaskType taskType)` <br>
      Submits a callable task to the thread pool with a specified TaskType. <br>
      The Future's get method will return the result of callable upon successful completion.
  
  - #### `submit(Callable<V> callable)` <br>
      Submits a callable task to the thread pool. The task will be assigned the default TaskType. <br>
      The Future's get method will return the result of callable upon successful completion.
  
  - #### `beforeExecute(Thread t, Runnable r)` <br>
      This method is called before a task is executed. <br>
      This method decrements the count of the priority of the task in the QueuePriorityArr.
  
  - #### `getCurrentMax()` <br>
      Returns the current maximum priority among the tasks in the queue.
  
  - #### `gracefullyTerminate()` <br>
      Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. <br>
      The method waits for the termination of all running tasks and for the tasks in the queue to be completed. <br>
      The method will wait for a maximum of 100 milliseconds for the termination of the tasks
      
  
## Ex2_2_Tests.java
  <ins>This class have the following Test methods:</ins> <br>
  - #### `partialTest()`
      A test that was given to us with the assignment.
  
  - #### `main()`
      Additional test of our own that creates 100 tasks and every time a task being added <br>
      to the CustomExecutor, the logger will print <br>
      the CustomExecutor toString INFO (Running/not Running, pool size, active threads, queued tasks, completed tasks). <br>
      After the CustomExecutor finish all of the tasks, the logger will print again the CustomExecutor toString INFO, <br>
      (Sum of 1 through 10, Reversed String, Total Price, Current maximum priority).

<I><B><ins>Tests passed</I></B></ins>
  
![Ex2_1_Tests](https://user-images.githubusercontent.com/102155998/212236374-71ff0d5f-7c13-4dcd-a5ad-6d17a48bb714.png)
  
  
  <I><B><ins>main Test output</I></B></ins>

![main test part 1](https://user-images.githubusercontent.com/102155998/212237031-0c1e5473-850a-462a-be4f-d442605e991f.png)
  
- #
- #
- #

![main test part 2](https://user-images.githubusercontent.com/102155998/212237116-db4bcc5d-a8d8-45d6-9223-338237a75761.png)


## Ex2_2_UML.png
  
  
![Ex2_2_UML](https://user-images.githubusercontent.com/102155998/212237279-534298b3-3c81-469e-8fcd-3acc1d7a0c15.png)



# Running
  - To run the repository, you will need to have JUnit 5 and a Java development environment set up. <br>
  
  - Clone this repository to your chosen location using the following command: <br>
  
  ```
  /path/of/your/chosen/location> git clone https://github.com/avivTurgeman/OOP_Assignment_2.git
  ```
  - Open the Ex2_1_Test.java/main.java/Ex2_2_Tests.java file in your preferred <br>
    Java development environment (e.g. Eclipse, IntelliJ IDEA, etc.). <br>
  
  - Use your development environment's built-in support for running JUnit tests <br>
    to run the test() method in the Tests class. This is usually done by right-clicking on the test method <br>
    and selecting "Run As" > "JUnit Test" (in Eclipse) <br>
    or by using a keyboard shortcut (e.g. Ctrl+Shift+F10 in IntelliJ IDEA).
  
















