# Poetry
开源的今日诗词，练手项目，代码写的比较low。

智能根据用户所在地返回诗词推荐;

#### 架构

Spring boot + MyBatis + Redis

* 另外有一份微服务分布式的还在练手中，太low了就不发出来了。

#### 数据来源

chinese-poetry：https://github.com/chinese-poetry/chinese-poetry

采用了唐代的数据并剔除了过长的古诗和存在乱码的古诗

#### 参与成员

@[轩辕子墨](https://github.com/xyzmos)，@[Aieszzz](https://github.com/Arieszzz),@[LiuXiao](https://github.com/lx4711)



#### 推荐因素

##### 地点：

城市，省，六大区域

##### 时间：

清晨、正午、傍晚、深夜

##### 季节

##### 天气



#### 预览地址

http://zpi.nextrt.com/poetry/api/get



#### 返回结果示例

``` JSON
{
  "status": 1,
  "msg": "查询成功",
  "data": {
    "tag": {
      "area": "华东",
      "weather": "云",
      "season": "夏",
      "time": "傍晚"
    },
    "poetry": {
      "poetryId": 22295,
      "subject": "过梅里七首　家于无锡四十载今敝庐数堵犹存今列题于后 翡翠坞",
      "dynasty": "唐",
      "author": "李绅",
      "content": "翡翠飞飞绕莲坞，一啄嘉鱼一鸣舞。莲茎触散莲叶欹，露滴珠光似还浦。虞人掠水轻浮弋，翡翠惊飞飞不息。直上层空翠影高，还向云间双比翼。弹射莫及弋不得，日暮虞人空叹息。"
    }
  }
}
```

