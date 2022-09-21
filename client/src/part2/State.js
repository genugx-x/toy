import {Component} from "react";

class State extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: this.props.name,
            number: 200
        }
    }

    changeState = (flag) => {
        if (flag) {
            this.state.name = "direct";
            this.forceUpdate(); // 강제 업데이트
        } else this.setState({name : "setState"});
    }

    render() {
        return (
            <div style={{padding: "0px"}}>
                <button onClick = {(e) => this.changeState(true)}> 직접 변경 </button>
                <button onClick = {(e) => this.changeState(false)}> setState 사용 변경 </button>
                <br/>
                state.name = {this.state.name}
            </div>
        )
    }
}

export default State;