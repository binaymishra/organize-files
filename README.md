# Organize Files
This repository is intended for creating a script to organize, group and sort files in a given folder.

##How to use

### Step-1
Clone repository
````
$ git clone <repo url>
````

Navigate to project folder:
````
$ cd organize-files
````

Validate:
```` 
$ pwd
````



### Step-2
````
$ ./gradlew clean build
````

### Step-3
Update your <folder path> in run.sh as exactly below e.g as sample below.
````
-Dinput.path='<your folder path>'
````

### Step-4
````
$ sh run.sh 
````