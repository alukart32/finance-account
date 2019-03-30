var app = new Vue({
    el:'#start-msg',
    data:{
        msg:'Index page - work footage'
    }
});

var app2 = new Vue({
   el:"#msgs",
   data:{
       list_msg:[
           {text: 'point one'},
           {text: 'point two'}
        ]
   }
});

var user_in_1 = new Vue({
   el:"#user_in_1",
   data:{
       msg:"Hello, user!"
   },
   methods:{
       reverseMsg: function () {
           this.msg = this.msg.split(' ').reverse().join(' ')
       }
   }
});