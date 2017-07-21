# AwesomeKeyboard

emojicons, emojicons gif ( like Skype), sticker, photo and color keyboard

### I. Demo


`apk demo`

 https://github.com/hoanganhtuan95ptit/AwesomeKeyboard/blob/master/output/app-debug.apk

`new emojicons gif ( like Skype) `

 ![alt text](output/Screenshot_2017-07-21-15-48-25.png)
 
 ` video demo`
 ![Sample Video](output/2017_07_21_08_28_10.mp4)
 
 ` gif demo `
 
 ![alt text](https://firebasestorage.googleapis.com/v0/b/hoanganhtuan-1070.appspot.com/o/ezgif.com-video-to-gif.gif?alt=media&token=a8fcfe81-219f-4542-80d8-85f59df35424)

`old `

| Emojicons | Sticker | Photo	| Color	| Nomal |
| -------- | -------- | -------- | -------- | -------- |
| ![alt text](output/Screenshot_2017-07-21-16-18-50.png)   | ![alt text](output/Screenshot_2017-07-21-16-19-01.png)  | ![alt text](output/Screenshot_2017-07-21-16-19-18.png)   | ![alt text](output/Screenshot_2017-07-21-16-19-28.png)   | ![alt text](output/Screenshot_2017-07-21-16-19-41.png)   |


### II. Setting
### III. Doc

#### 1.Add

* Application:

```java
     public class App extends Application {

         @Override
         public void onCreate() {
             super.onCreate();
             AwesomeKeyboardSdk.initialize(this, Utils.emoticons);
         }
     }
```

* Use:

```java
     private void initKeyboard() {
            keyboard = KeyboardBuilder.with(this)
                    .setRoot(reRoot)
                    .setKeyboardLayout(keyboardLayout)
                    .setOnKeyDownListener(this)
                    .addEditText(editMessage)
                    .builder();
     }
```
* EmojiconTextView:

```java
      <com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconTextView
         android:id="@+id/tv"
         android:layout_width="match_parent"
         android:layout_height="wrap_content" />
```
`set text`
```java
     EmojiconTextView.setText("infor");
```
* EmojiconTextView:

```java
       <com.hoanganhtuan95ptit.awesomekeyboard.core.view.EmojiconEditText
            android:id="@+id/edit_message"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/transparent"
            android:hint="Message"
            android:maxHeight="100dp"
            android:minHeight="50dp"
            android:padding="8dp" />
```
`set text`
```java
      @Override
     public void onItemEmoticonClicked(String key) {
         int start = editMessage.getSelectionStart();
         int end = editMessage.getSelectionEnd();
         editMessage.getEditableText().replace(start, end, key);
     }
```



* Show keyboard

`1. show sticker `

```java
        keyboard.showKeyboard(AwesomeKeyboardType.STICKER.getValue());
```
`2. show photo `

```java
       keyboard.showKeyboard(AwesomeKeyboardType.PHOTO.getValue());
```
`3. show color `

```java
        keyboard.showKeyboard(AwesomeKeyboardType.COLOR.getValue());
```
`4. show nomal `

```java
         keyboard.showKeyboard(AwesomeKeyboardType.NOMAL.getValue());
```
* Hide keyboard

```java
          keyboard.hideAllKeyboard();
```
* update Data Emoticon

```java
          keyboardLayout.updateDataEmoticon(emoticonCategories.get(i));
```
* update Data Sticker

```java
          keyboardLayout.updateDataSticker(stickerCategories.get(i));
```
* update Data Color

```java
          keyboardLayout.updateDataColor("#1ecef3");
```
