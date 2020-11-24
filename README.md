# CustomAlert

## General

Custom alert for a better design of your application.

### Gradle
Project
```gladle
allprojects {
        repositories {
	        ...
		maven { url 'https://jitpack.io' }
	}
}
```
App
```gladle
dependencies {
        implementation 'com.github.MH-projects:CustomAlert:release'
}
```
### Maven
```maven
<repositories>
        <repository>
                <id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

```maven
<dependency>
        <groupId>com.github.MH-projects</groupId>
	<artifactId>CustomAlert</artifactId>
	<version>release</version>
</dependency>
```



## Usage

```java
final CustomAlert customAlert = new CustomAlert(this, Theme.SYSTEM);
        customAlert.setType(Type.SUCCESS);
        customAlert.setTitle("Success!");
        customAlert.setMessage("Your profile is update");
        customAlert.setPositiveText("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAlert.dismiss();
            }
        });
        customAlert.show();
```
### Theme
  - Theme.SYSTEM
  - Theme.LIGHT
  - Theme.DARK
  
<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success_dark.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail_dark.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning_dark.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_prg_dark.gif" width="200" height="355">
</p>
  
### Type
  - Type.SUCCESS
  - Type.FAIL
  - Type.WARNING
  - Type.PROGRESS

<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_prg.gif" width="200" height="355">
</p>

If you want to set custom alert full, add this line in your code.
```java
customAlert.setFullAlert(true);
```
<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success_full.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail_full.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning_full.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_prg_full.gif" width="200" height="355">
</p>

### View
If you want to add view, add this line in your code.
```java
alert.setView(getLayoutInflater().inflate(R.layout.custom_view, null));
```
<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/view_simple.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/view_simple_full.png" width="200" height="355">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/view_large.gif" width="200" height="355">
</p>

If you want to add a gif, add this line in your code. *The gif adjust to 350px height*  
```java
alert.setGif(R.drawable.your_gif);
```
<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/view_gif.gif" width="200" height="355">
</p>

## Custom

| Method | Parameter  | Description  |
| ------- | --- | --- |
| setFullAlert | boolean | Set the alert is full or half. |
| hideClose | boolean | Hide the close icon. |
| setTitle | String title | Set the title. |
| setMessage | String message | Set the message.  |
| setColor | int R.color.your_color | Set the circle color. |
| setColorTitle | int R.color.your_color | Set the title color. |
| setColorMessage | int R.color.your_color | Set the message color. |
| setColorPrg | int R.color.your_color | Set the progress color. |
| setIcon | int R.drawable.your_drawable | Set the circle icon. If the icon is *null* the circle is gone. |
| set **Neutral \| Negative \| Positive** Text | String text | Set the neutral,negative or positive button text. |
| set **Neutral \| Negative \| Positive** Text | String text, View.OnClickListener listener| Set the neutral,negative or positive button text and set the listener of button. |
| set **Neutral \| Negative \| Positive** TextColor | R.color.your_color | Set the button text color |
| set **Neutral \| Negative \| Positive** BackColor | R.color.your_color | Set the button background color|
| set **Neutral \| Negative \| Positive** ColorPress | R.color.your_color, int alpha | Set the button background color when pressed |