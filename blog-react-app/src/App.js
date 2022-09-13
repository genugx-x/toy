import React from 'react';
import Todo from './Todo';
import AddTodo from "./AddTodo";
import './App.css';
import { Paper, List, Container, Grid, Button, AppBar, Toolbar, Typography } from "@mui/material";
import { call, signout } from "./service/ApiService";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            loading: true,
        };
    }

    componentDidMount() {
        call("/todo", "GET", null).then((response) =>
            this.setState({ items: response.data, loading: false })
        );
        console.log(this.state);
    }

    add = (item) => {
        call("/todo", "POST", item).then((response) =>
            this.setState({ items: response.data })
        );
    }

    delete = (item) => {
        call("/todo", "DELETE", item).then((response) =>
            this.setState({ items: response.data })
        );
    }

    update = (item) => {
        call("/todo", "PUT", item).then((response) =>
            this.setState({ item: response.data })
        );
    }

    render() {
        var todoItems = this.state.items.length > 0 && (
            <Paper style={{ margin: 16}}>
                <List>
                    {this.state.items.map((item, idx) => (
                        <Todo
                            item={item}
                            key={item.id}
                            delete={this.delete}
                            update={this.update}
                        />
                    ))}
                </List>
            </Paper>
        );

        var navigationBar = (
          <AppBar position="static">
              <Toolbar>
                  <Grid justifyContent="space-between" container>
                      <Grid item>
                          <Typography variant="h6">오늘의 할일</Typography>
                      </Grid>
                      <Grid>
                          <Button color="inherit" onClick={signout}>
                              로그아웃
                          </Button>
                      </Grid>
                  </Grid>
              </Toolbar>
          </AppBar>
        );

        var todoListPage = (
          <div>
              {navigationBar} {/* 내비게이션 바 렌더링 */}
              <Container maxWidth="md">
                  <AddTodo add={this.add} />
                  <div className="TodoList">{todoItems}</div>
              </Container>
          </div>
        );

        var loadingPage = <h1> 로딩중.. </h1>;
        var content = loadingPage;
        console.log(this.state)
        if (!this.state.loading) {
            content = todoListPage;
        }

        return <div className="App">{content}</div>
    }
}

export default App;