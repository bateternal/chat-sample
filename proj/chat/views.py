# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render,redirect
from .forms import Form,c,sign,Chat,name,auth
import requests
from django.http import HttpResponse,HttpResponseRedirect
from django.contrib.sessions.backends.db import SessionStore
# Create your views here.


def create(request):
    # print(dir(models))
    s = SessionStore()
    form = Form(request.POST or None)
    a = c()
    print(0)
    if request.method == 'POST':

        form = Form(request.POST or None)
        
        print(1)
        
        if form.is_valid():
            print(2)            

            name = form.cleaned_data['name']
            lastname = form.cleaned_data['lastname']
            username = form.cleaned_data['username']
            password = form.cleaned_data['password']
            p = form.cleaned_data['password2']
            print password
            r = requests.post('http://127.0.0.1:9090/create',data={
            	'name':name,
            	'last':lastname,
            	'uname':username,
            	'pass':password
            	})
            print r.text
            if r.text[:3]=="sor":
            	a.tata = True
            if p!= password and not a.tata:
            	print "okokokook"
            	a.inin = True
            if (not a.inin) and (not a.tata):
            	request.session['test']=12345
            	request.session['user']=username
            	s.save()
            	return HttpResponseRedirect('/chat/')
            # newdoc.save()
            
            
        else:
            print("nashod")
    return render(request, 'upload.html', {'form': form,'a':a})

def close(request):
	try:
		a = request.session.pop('test')
		b = request.session.pop('user')
		print a,b
	except:
		print "ajab"
		pass
	return HttpResponseRedirect('/sign/')

def chat(request):
	a = auth()
	s = SessionStore()
	t = {}
	fo = Chat(request.POST or None)
	n = name()
	try:
		user = request.session['user']
		r = requests.post('http://127.0.0.1:8080/getname',data={'uname':user})
		print r.text
		n.name = r.text
		rr = requests.post('http://127.0.0.1:8080/getauth',data={'uname':user})
		a.token = rr.text
		a.uname = user
	except Exception as e:
			pass
	if request.method == 'POST':
		if fo.is_valid():
			print 23456
			try:
				if request.session['test']==12345:
					print fo.cleaned_data
					content = fo.cleaned_data['content']
					uname = fo.cleaned_data['username']
					r = requests.post('http://127.0.0.1:8080/send',data={'uname':uname,'content':content})
					print 22
					print r.text
					if r.text=="ok":
						print 22
						print r.text
						return HttpResponse('sent')
					else:
						return HttpResponse('please test another time!')
					print user,1234254
					t = {'form':fo,'r':a}
			except Exception as e:
				print e
				pass
		else:
			print "nashod"
	else:
		t = {'form':fo,'s':n,'r':a}
	return render(request, 'chat.html', t)
	
def signin(request):
	try:
		if request.session['test']==12345:
			return HttpResponseRedirect('/chat/')
		
	except Exception as e:
		pass
	
	s = SessionStore()

	formm = sign(request.POST or None)

	a = c()
	if request.method == 'POST':
		if formm.is_valid():
			#formm = sign(request.POST or None)
			uname = formm.cleaned_data['username']
			password = formm.cleaned_data['password']
			print uname,password
			r = requests.post('http://127.0.0.1:9090/check',data={
				'uname':uname,
				'pass':password
				})
			print r.text
			if r.text!="1":
				a.inin = True
			request.session['test']=12345
			request.session['user']=uname
			s.save()
			return HttpResponseRedirect('/chat/')
		else:
			print("nashod")
	return render(request,'sign.html',{'form':formm,'a':a})



