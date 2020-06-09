# CountDownTimerSupport
Android倒计时器，支持开始倒计时、暂停倒计时、继续倒计时、停止倒计时、重新倒计时

## 示例APK
[example-1.0.3.apk](example-1.0.3.apk)

## 效果演示
![](screenshot/count-down-timer.gif)
![](screenshot/count-down-timer-2.gif)

## 快速开始
### Android Studio - 在build.gradle中引入
```java
implementation 'in.xiandan:count-down-timer:1.0.3'
```

### 旧版请谨慎升级到1.0.3以上
> 1.启用了新的包名，升级后需要在用到的地方修改为新的包名
> 
> 2.onFinish的回调时机调整为与[CountDownTimer#onFinish()](https://developer.android.com/reference/android/os/CountDownTimer#onFinish())相同

#### 初始化
```
//总时长 间隔时间
CountDownTimerSupport mTimer = new CountDownTimerSupport(millisInFuture, countDownInterval);
// SimpleOnCountDownTimerListener
mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
    @Override
    public void onTick(long millisUntilFinished) {
        // 倒计时间隔
    }

    @Override
    public void onFinish() {
        // 倒计时结束
    }

    @Override
    public void onCancel() {
        // 倒计时手动停止
    }
});
```

#### 启动倒计时
```
mTimer.start();
```

#### 暂停倒计时
```
mTimer.pause();
```

#### 恢复倒计时
```
mTimer.resume();
```

#### 停止倒计时
```
mTimer.stop();
```

#### 重置并启动倒计时
```
mTimer.reset();
mTimer.start();
```
