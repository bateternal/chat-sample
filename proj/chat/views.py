# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render,redirect
from .forms import Form,c,sign
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
            	return HttpResponseRedirect('/login/')
            # newdoc.save()
            
            
        else:
            print("nashod")
    return render(request, 'upload.html', {'form': form,'a':a})
def chat(request):
	#request.session.test_cookie_worked():
	if request.session['test']==12345:
		user = request.session['user']
			# code here
		return HttpResponse("you joined!")
	else:
		return HttpResponseRedirect('/signin/')
	
def signin(request):
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
		else:
			print("nashod")
	return render(request,'sign.html',{'form':formm,'a':a})



