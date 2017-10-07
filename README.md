# common
封装通用性代码，可以提高开发效率，下面逐个结介绍每个工具包
## common-assist
通用工具类，字符，流、等都在此处找到 util，是其他工程的基类

## common-dao
数据库访问层封装-待完成

## common-excel
excel 读取工具类，可以读取超大文件，40MB+和100W行的数据都没问题

## third-wechat
wechet 的工具类


## common-upgradecs
在传统的项目中，目录结构如下
DEMO
  |---WebRoot
      |---WEB-INF
          |---classes
      |---META-INF
### 当我们需要增量更新 jsp、css、java 等文件的时候。我们需要一个一个去找，然后在再建立文件，然后在更新到服务器。二个字，麻烦。。。。但是通过此工程就可以实现帮你做到按目录构建出增量的升级文件。直接到服务器替换即可。


