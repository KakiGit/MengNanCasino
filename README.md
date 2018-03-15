# 猛男赌场

看了老菊视频里的赌场之后想知道这样的赌博亏不亏,于是写了这个程序看了一下. 结果表明这游戏有先手优势.

* 玩家后手每次赢钱期望14块,如果平局也输钱,每次赢钱期望5-6块

* 先手胜率49%

* 后手胜率42%

* 平局9%

老菊视频里银行总是先手,所以继续赌下去会亏.

![Image text](https://github.com/KakiGit/MengNanCasino/raw/master/result.png)
```bash
老菊视频
https://www.bilibili.com/video/av20764360/
```

## Bug fixes
感谢以下B站用户的提醒

@诸葛孔明Alter: 输了-100,赢了+150,应该算亏不亏

@DreamNya: 骰子有6个面,2个攻击2个再来一次,且有平局