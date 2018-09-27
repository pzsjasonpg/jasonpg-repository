import urllib.request
import re

for count in range(1,1000):
    print("下载第%d页图片"%count)
    url = 'http://www.quanjing.com/category/118291/%d.html'%count

    page = urllib.request.urlopen(url)
    html = page.read()

    reg = "http://.+?\\.jpg"
    img = re.compile(reg)
    html = html.decode('utf-8')

    imgList = re.findall(img, html)
    print(imgList)

    # 数组去重
    newList = []
    for i in imgList:
        if i not in newList:
            newList.append(i)
    j = 0
    for i in newList:
        print(newList[j] + "将被下载")
        urllib.request.urlretrieve(newList[j], 'D:/photo/%d_%d.jpg' % (j,count))
        print("下载成功", "目的：D:/photo/%d_%d.jpg" % (j,count))
        j = j + 1


