var menus = [{
    action: "mdi-home-city-outline",
    title: "首页",
    path: "/admin",
    items: [{
      title: "统计",
      path: "/main"
    }]
  },
  {
    action: "mdi-star-face",
    title: "动态",
    path: "/dynamic",
    items: [{
        title: "动态列表",
        path: "/list"
      },
      {
        title: "动态举报列表",
        path: "/ReportList"
      },
      {
        title: "分类管理",
        path: "/category"
      },
      {
        title: "分类删除",
        path: "/CategoryDelete"
      },
      {
        title: "动态删除",
        path: "/delete"
      }
    ]
  }, {
    action: "mdi-music-note",
    title: "音乐管理",
    path: "/music",
    items: [{
        title: "音乐统计",
        path: "/statistics"
      },
      {
        title: "音乐管理",
        path: "/list"
      },
      {
        title: "音乐删除",
        path: "/delete"
      }
    ]
  },
  {
    action: "mdi-account-multiple",
    title: "用户管理",
    path: "/user",
    items: [{
        title: "用户管理",
        path: "/list"
      },
      {
        title: "用户删除",
        path: "/delete"
      }
    ]
  },
  {
    action: "mdi-cogs",
    title: "管理员管理",
    path: "/setting",
    items: [{
        title: "管理员管理",
        path: "/list"
      },
      {
        title: "管理员删除",
        path: "/delete"
      }
    ]
  }
]

export default menus;