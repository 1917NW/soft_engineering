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

  
     

]

const router = new VueRouter({
  routes
})

export default router
