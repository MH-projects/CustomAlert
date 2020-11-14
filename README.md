# CustomAlert

## General

Custom alert for a better design of your application.

# Usage

```java
final CustomAlert customAlert = new CustomAlert(this);
        customAlert.setType(Type.SUCCESS);
        customAlert.setTitle("Success!");
        customAlert.setMessage("Your profile is update");
        customAlert.setButton("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAlert.dismiss();
            }
        });
        customAlert.show();
```

<p>
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_success.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_fail.png" width="200">
<img src="https://github.com/MH-projects/CustomAlert/blob/master/pictures/type_warning.png" width="200">
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