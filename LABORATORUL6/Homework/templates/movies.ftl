<!DOCTYPE html>
<html>
<head>
    <title>Movie Report</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
        }

        th {
            background-color: #ddd;
        }
    </style>
</head>

<body>

<h1>Movie Report</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Release</th>
        <th>Duration</th>
        <th>Score</th>
        <th>Genre</th>
    </tr>

    <#list movies as movie>
        <tr>
            <td>${movie.id}</td>

            <td>${movie.title}</td>

            <td>${movie.release_date}</td>

            <td>${movie.duration}</td>

            <td>${movie.score}</td>

            <td>${movie.genre}</td>
        </tr>
    </#list>
</table>

</body>
</html>