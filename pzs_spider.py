import scrapy

class pzs(scrapy.Spider):#需要继承scrapy.Spider类
    name = "pzs" #define spider's name

    #start_urls数组和方法start_requests相同的功能，两者二选一即可，但是选择start_urls就必须有parse方法
    start_urls = [
            'http://lab.scrapyd.cn/page/1/',
            'http://lab.scrapyd.cn/page/2/'
        ]
    # def start_requests(self):#又此方法通过下面连接爬取页面
    #     #定义爬取的链接
    #     urls = [
    #         'http://lab.scrapyd.cn/page/1/',
    #         'http://lab.scrapyd.cn/page/2/'
    #     ]
    #     for url in urls:
    #         yield scrapy.Request(url=url,callback=self.parse)#提交的页面如何处理，提交给parse方法处理

    def parse(self, response):
        page = response.url.split("/")[-2] #提取分页
        filename = 'pzs-%s.html' % page
        with open(filename,'wb') as f:
            f.write(response.body) #刚才下载的页面去哪里？response.body代表刚才我们下载的页面
        self.log('保存文件: %s' % filename)

        # 1、定义链接；
        # 2、通过链接爬取（下载）页面；
        # 3、定义规则，然后提取数据；