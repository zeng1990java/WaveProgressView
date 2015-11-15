# WaveProgressView
WaveProgressView

a simple progress view.

<img src="/image/wave_progress_view.gif" alt="progress-circular-indeterminate" title="progress-circular-indeterminate" width="477" height="791" />

###gradle
/build.gradle
~~~
jitpack.io

repositories {
    maven {
        url "https://jitpack.io"
    }
}
~~~
/app/build.gradle
~~~
compile 'com.github.zeng1990java:WaveProgressView:1.0.5'
~~~
###usage
```xml
<com.github.zeng1990java.widget.WaveProgressView
        android:id="@+id/wave_progress_view"
        android:layout_width="120dp"
        android:layout_height="80dp"
        android:layout_centerInParent="true"
        />

<com.github.zeng1990java.widget.WaveProgressView
  android:id="@+id/wave_progress_view_2"
  android:layout_width="120dp"
  android:layout_height="80dp"
  android:layout_alignParentBottom="true"
  android:layout_marginBottom="10dp"
  android:layout_centerHorizontal="true"
  app:waveMax="100"
  app:waveColor="#3498db"
  app:waveAmplitude="4dp"
  app:waveBorderWidth="3dp"
  app:waveBorderRadius="2dp"
  />
```

License
============

The MIT License (MIT)

Copyright (c) 2015 zeng1990java

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
