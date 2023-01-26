# PostalOff

აპლიკაციის ფუნქციონალი -> საწყის სქრინზე მომხმარებელს შეჰყავს ამანათის ინფორმაცია, როგორიცაა: თრექინგ აიდი, ფასი, ნივთის ლინკი, მისი ფოტო, კომენტარები და ა.შ. ამის შემდეგ მომხმარებელს შეუძლია აღნიშნული ამანათი დაამატოს ყველა შეკვეთის სიაში რომლის ნახვაც შეეძლება შემდეგ სქრინზე. 
![Main](https://user-images.githubusercontent.com/74249289/214776507-1105a6ec-3304-4dca-a327-e516c6a2ad89.jpg)
![BR_fields](https://user-images.githubusercontent.com/74249289/214776550-8f4c9d53-0ff4-4988-917a-507e194b7e0d.jpg)

შეკვეთების სქრინზე უკვე ჩანს ყველა დამატებული ამანათის ინფორმაცია, არსებობის შემთხვევაში ფოტოსთან ერთად. 

 ![BR_added_item](https://user-images.githubusercontent.com/74249289/214776586-34cb45b8-968e-4be9-853d-0933c62c23b9.jpg)

![Slider](https://user-images.githubusercontent.com/74249289/214776595-58a72e8f-f6ff-4cbf-9278-59b91528763e.jpg)

გამოყენებული ტექნოლოგიები: 

Glide -> ფოტოების ჩასატვირთად 

SQLiteHelper -> ამანათების ქეშირებისთვის 

BroadCastReceiver -> გლობალური ივენთების დასაჭერად 

NotificationManager -> სისტემური ნოტიფიკაციების გამოსაჩენად 

ViewPager, RecyclerView -> სიების გამოსაჩენად