import scrapy

class pzs(scrapy.Spider):#需要继承scrapy.Spider类
    name = "pzs" #define spider's name

    start_urls = [
            'http://lab.scrapyd.cn/page/1/',
            'http://lab.scrapyd.cn/page/2/'

    def parse(self, response):
        pzs = response.css('div.quote')[0]
        text = pzs.css('.text::text').extract_first()
        autor = pzs.css('.author::text').extract_first()
        tags = pzs.css('.tags .tag::text').extract()
        tags = ','.join(tags) #数组转换成字符串

        filename = '%s-语录.txt' % autor
        f = open(filename,'a+')
        f.write(text)
        f.write('\n')
        f.write('标签：'+tags)
        f.close()
