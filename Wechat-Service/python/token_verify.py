import web
#from handle import Handle

urls = (
    '/wx', 'Handle',
)


class Handle(object):
    def GET(self):
        return "hello, this is handle view"

if __name__ == '__main__':
    app = web.application(urls, globals())
    app.run()
