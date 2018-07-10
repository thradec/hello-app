import 'package:flutter/material.dart';
import 'package:hello_flutter/hello_home.dart';
import 'package:hello_flutter/hello_list.dart';

void main() => runApp(App());

class App extends StatefulWidget {
  @override
  _AppState createState() => _AppState();
}

class _AppState extends State<App> with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'hello-app',
        home: Scaffold(
            appBar: AppBar(
              title: Text("hello-app"),
            ),
            body: TabBarView(
                controller: _tabController,
                children: [HelloHome(), HelloList()]),
            bottomNavigationBar: Material(
              color: Colors.blue,
              child: TabBar(controller: _tabController, tabs: [
                Tab(icon: Icon(Icons.home)),
                Tab(icon: Icon(Icons.list))
              ]),
            )));
  }
}

class AppContext {
  static final AppContext instance = AppContext();

  final List<Hello> hellos = [
    Hello(1, "ahoj"),
    Hello(2, "hello"),
    Hello(3, "tschus"),
    Hello(4, "salut"),
    Hello(5, "ciao")
  ];
}

class Hello {
  final int id;
  final String message;

  Hello(this.id, this.message);
}
