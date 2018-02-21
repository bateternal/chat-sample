from django.conf.urls import url
import views

urlpatterns = [
	url(r'^create/$',views.create,name='create'),
	url(r'^chat/$',views.chat,name='chat'),
	url(r'^sign/$',views.signin,name='sign'),
	url(r'^close/$',views.close,name='close'),
]
