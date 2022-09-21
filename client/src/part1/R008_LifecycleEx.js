import { Component } from "react";

class R008_LifecycleEx extends Component {
    // 1
    constructor(props) {
        super(props);
        this.state = {};
        console.log('1. constructor call');
    }

    // 2
    static getDerivedStateFromProps(props, state) {
        console.log('2. getDerivedStateFromProps call : ' + props.prop_value);
        return {tmp_state:props.prop_value};
    }

    // 3
    render() {
        console.log('3. render call');
        return (
            <h2>[THIS IS shouldComponentUpdate FUNCTION]</h2>
        )
    }

    // 4
    componentDidMount() {
        console.log('4. componentDidMount call');
        console.log('5. tmp_state : ' + this.state.tmp_state);
        this.setState({tmp_state2 : true});
    }

    // 5 - return 값이 true인 경우, render() 함수를 한번 더 호출
    shouldComponentUpdate(nextProps, nextState, nextContext) {
        console.log('6. shouldComponentUpdate call / tmp_state2 = ' + nextState.tmp_state2);
        return nextState.tmp_state2
    }
}

export default R008_LifecycleEx