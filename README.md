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
compile 'com.github.zeng1990java:WaveProgressView:1.0.2'
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
