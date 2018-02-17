from django.conf.urls import url
import views

urlpatterns = [
	url(r'^create/$',views.create,name='create'),
	url(r'^chat/$',views.chat,name='login'),
	url(r'^sign/$',views.signin,name='sign'),
]
