# CustomAlert

## General

Custom alert for a better design of your application.

## Usage

```java
final CustomAlert customAlert = new CustomAlert(this);
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
  - Type.SUCCESS
  - Type.FAIL
  - Type.WARNING
  - Type.PROGRESS

<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_prg.gif" width="200">
</p>

If you want to set custom alert full, add this line in your code.
```java
customAlert.setFullAlert(true);
```
<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success_full.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail_full.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning_full.png" width="200">
</p>

## Custom

| Method | Parameter  | Description  |
| ------- | --- | --- |
| setFullAlert | boolean | Set the alert is full or half. |
| hideClose | boolean | Hide the close icon. |
| setTitle | String title | Set the title. |
| setMessage | String message | Set the message.  |
| setColor | R.color.your_color | Set the color of the circle. |
| setIcon | R.drawable.your_drawable | Set the icon of the circle. If the icon is *null* the circle is gone. |
| set **Neutral \| Negative \| Positive** Text | String text | Set the neutral,negative or positive button text. |
| set **Neutral \| Negative \| Positive** Text | String text, View.OnClickListener listener| Set the neutral,negative or positive button text and set the listener of button. |
| set **Neutral \| Negative \| Positive** TextColor | R.color.your_color | Set the button text color |
| set **Neutral \| Negative \| Positive** BackColor | R.color.your_color | Set the button background color|
| set **Neutral \| Negative \| Positive** ColorPress | R.color.your_color, int alpha | Set the button background color when pressed |