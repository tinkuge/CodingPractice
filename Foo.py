class Foo:
    def __init__(self):
        print("In init")
    def bar(self):
        print("Foo.bar called")
        return self
    def baz(self):
        print("Foo.baz called")
        return self
        

foo = Foo()

print("First statement")
print(foo.bar().baz())

print("Second Statement")
print(foo.baz().bar())
