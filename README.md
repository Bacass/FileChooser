# FileChooser
Simple Android File Chooser

# Features:
* You can browse different types of files.

# Usage
1. Gradle dependency:

	```groovy
	allprojects {
	   repositories {
           	maven { url "https://jitpack.io" }
	   }
	}
	```

    ```groovy
   implementation 'com.github.bacass:FileChooser:0.0.4'
    ```

   ** If you are using a AndroidStudio higher than version Arktic Fox, please set it in the [settings.gradle] file.
   
   ```groovy
   dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven { url 'https://jitpack.io' }
        }
   }
   ```

# Customization
*  Pick files..
*  There are many different mime types (*/*, , video/*, audio/*, application/pdf, application/zip ...)

	```java
	 new FileChooser(this)
            .start(RESULT_CODE_FILESELECT);
    ```

    ```java
	 new FileChooser(this)
            .setMimeType("*/*")
            .setMultipleChoose(true)
            .start(RESULT_CODE_FILESELECT);
    ```

    ```java
	 new FileChooser(this)
            .setMimeType("image/*")
            .setMultipleChoose(true)
            .start(RESULT_CODE_FILESELECT);
    ```

    ```java
	 new FileChooser(this)
            .setMimeType("video/*")
            .setMultipleChoose(true)
            .start(RESULT_CODE_FILESELECT);
    ```

# Libraries Used
* [From this link](https://o7planning.org/12725/create-a-simple-file-chooser-in-android#a61685561)

## License

    Copyright, bacass

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



