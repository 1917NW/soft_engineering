import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/login'
    
  },
 
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  
  {
    path:"/login",
    name : 'login',
    component: LoginView
  },
  {
    path:"/register",
    name : 'register',
    component: RegisterView
  },
  {
    path:"/admin",
    name : "admin_home",
    component: () => import("../views/admin/AdminHomeView.vue"),
    children:[
      {
        path:"/userlist",
        name:"userList",
        component : () => import("@/components/admin/UserList.vue")
      },
      {
        path:"/adminlist",
        name : "adminList",
        component:()=>import("@/components/admin/AdminList.vue")
      },
      {
        path:"/teacherlist",
        name : "teacherList",
        component:()=>import("@/components/admin/TeacherList.vue")
      },
      {
        path:"/questionlist",
        name :"questionlist",
        component:()=>import("@/components/admin/QuestionList.vue")
      },
      {
        path:"/paperlist",
        name:"paperlist",
        component:()=>import("@/components/admin/PaperList.vue")
      },
      {
        path:"/addpaper",
        name:"addpaper",
        component:()=>import("@/components/admin/AddPaper.vue")
      },
      {
        path:"/addquestion",
        name : "addquestion",
        component:()=>import("@/components/admin/AddQuestion.vue"),
        children:[{
            path : "/single",
            name:"single",
            component:()=>import("@/components/admin/question/single.vue")
        },
        
        {
          path:"/selectWord",
          name:"selectWord",
          component : () => import("@/components/admin/question/selectWord.vue")
        },
        {
          path:"/translate",
          name : "translate",
          component : () => import("@/components/admin/question/translate.vue")
        },
        {
          path : "/composition",
          name : "composition",
          component : () => import("@/components/admin/question/composition.vue")
        },
        {
          path : "/readselect",
          name : "readselect",
          component : () => import("@/components/admin/question/readselect.vue")
        },
        {
          path : "/selectPara",
          name : "selectPara",
          component : () => import("@/components/admin/question/selectPara")
        }
        ]
      },
      {
        path:"/examlist",
        name:"examlist",
        component: () => import("@/components/admin/ExamList.vue")
        
      },
      {
        path:"/addexam",
        name:"addexam",
        component:()=>import("@/components/admin/AddExam.vue")
      },
      {
        path:"/editexam",
        name:"editexam",
        component: () => import("@/components/admin/EditExam.vue")
      }
    ]
  },

  {
    path:"/student",
    name : 'student',
    component: () => import("@/views/student/StudentHomeView.vue"),
    children:[
        {
          path:"/studentExamList",
          name:"examlist",
          component:() => import("@/components/student/ExamList.vue")
        },
        {
          path:"/studentMyExams",
          name:"studentMyExams",
          component:() => import("@/components/student/MyExam.vue")

        },
        {
          path:"/studentExamScore",
          name:"studentExamScore",
          component:() => import("@/components/student/ExamScore.vue")
        }
    ]
  },
  {
    path:"/teacher",
    name:"teacher",
    component: () => import("@/views/teacher/TeacherHomeView.vue"),
    children : [
      {
        path:"/tquestionlist",
        name :"tquestionlist",
        component:()=>import("@/components/teacher/QuestionList.vue")
      },
      {
        path:"/tpaperlist",
        name:"tpaperlist",
        component:()=>import("@/components/teacher/PaperList.vue")
      },
      {
        path:"/taddpaper",
        name:"taddpaper",
        component:()=>import("@/components/teacher/AddPaper.vue")
      },
      {
        path:"/readoverexam",
        name:"readoverexam",
        component : () => import("@/components/teacher/ReadOverExam.vue")
      },{
        path:"/readover",
        name:"readover",
        component : () => import("@/components/teacher/ReadOver.vue")
      },
      {
        path:"/taddquestion",
        name : "taddquestion",
        component:()=>import("@/components/teacher/AddQuestion.vue"),
        children:[{
            path : "/tsingle",
            name:"tsingle",
            component:()=>import("@/components/teacher/question/single.vue")
        },
        
        {
          path:"/tselectWord",
          name:"tselectWord",
          component : () => import("@/components/teacher/question/selectWord.vue")
        },
        {
          path:"/ttranslate",
          name : "ttranslate",
          component : () => import("@/components/teacher/question/translate.vue")
        },
        {
          path : "/tcomposition",
          name : "tcomposition",
          component : () => import("@/components/teacher/question/composition.vue")
        },
        {
          path : "/treadselect",
          name : "treadselect",
          component : () => import("@/components/teacher/question/readselect.vue")
        },
        {
          path : "/tselectPara",
          name : "tselectPara",
          component : () => import("@/components/teacher/question/selectPara")
        }
        ]
      },
    ]
  },
  {
    path:"/do",
    name:"doexam",
    component:() => import("@/components/student/DoExam.vue"),
   
    
  },
  {
    path:"/check",
    name:"check",
    component : () => import("@/components/student/CheckExam.vue")
  },
  {
    path:"/point",
    name : "point",
    component : () => import("@/components/teacher/Point.vue")
    
  }

  
     

]

const router = new VueRouter({
  routes
})

export default router
