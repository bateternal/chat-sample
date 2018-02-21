from django import forms

class Form(forms.Form):
	name = forms.CharField(label='name', max_length=100)
	lastname = forms.CharField(label='lastname', max_length=100)
	username = forms.CharField(label='username', max_length=100)
	password = forms.CharField(widget=forms.PasswordInput())
	password2 = forms.CharField(widget=forms.PasswordInput())

class sign(forms.Form):
	username = forms.CharField(label='username', max_length=100)
	password = forms.CharField(widget=forms.PasswordInput())   

class c():
	inin = False
	tata = False

class Chat(forms.Form):
	content = forms.CharField(label='content',widget=forms.Textarea)
	username = forms.CharField(label='username',max_length=100)

class name():
	name = "unknown"

class auth():
	token = ""
	uname = ""