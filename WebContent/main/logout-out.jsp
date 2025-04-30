<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>

    <meta charset="UTF-8">
    <title>ログアウト</title>
    <style>
        #header {
            padding: 1rem;
            background-color: #f2f2f2;
        }
        #title {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .logout-box {
            background-color: grey;
            padding: 0.5rem;
            margin-top: 1rem;
            color: white;
        }
        .logout-box h2 {
            margin: 0;
            font-size: 1.2rem;
        }
        .logout-message {
            background-color: #a5d6a7;
            padding: 0.8rem;
            margin-top: 1rem;
            border: 1px ;
            border-radius: 4px;
            text-align: center;
            font-weight: bold;

        }
    </style>
</head>
<body>

<div id="header">
    <div id="title">得点管理システム</div>
</div>

<div class="logout-box">
    <h2>ログアウト</h2>
</div>

<p class="logout-message">ログアウトしました。</p>

<a href="login-in.jsp">ログイン</a>
<%@include file="../footer.html" %>