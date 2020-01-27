import React, {Component} from 'react';
import LoadingIndicator from '../common/LoadingIndicator';
import {Button, Icon, notification} from 'antd';
import {withRouter} from 'react-router-dom';
import './NoteList.css';

class NoteList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            notes: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadNoteList = this.loadNoteList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    componentDidMount() {
        this.loadNoteList();
    }

    componentDidUpdate(nextProps) {
        if (this.props.isAuthenticated !== nextProps.isAuthenticated) {
            // Reset State
            this.setState({
                notes: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                isLoading: false
            });
            this.loadNoteList();
        }
    }

    loadNoteList(page = 0, size) {
        let promise;

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {
                const polls = this.state.polls.slice();

                this.setState({
                    notes: polls.concat(response.content),
                    page: response.page,
                    size: response.size,
                    totalElements: response.totalElements,
                    totalPages: response.totalPages,
                    last: response.last,
                    isLoading: false
                })
            }).catch(error => {
            this.setState({
                isLoading: false
            })
        });

    }

    handleLoadMore() {
        this.loadNoteList(this.state.page + 1);
    }

    handleNoteChange(event, noteIndex) {
        const currentVotes = this.state.currentVotes.slice();
        currentVotes[noteIndex] = event.target.value;

        this.setState({
            currentVotes: currentVotes
        });
    }


    handleNoteSubmit(event, pollIndex) {
        event.preventDefault();
        if (!this.props.isAuthenticated) {
            this.props.history.push("/login");
            notification.info({
                message: 'Note',
                description: "Please login to create Note.",
            });
            return;
        }

        const poll = this.state.polls[pollIndex];
        const selectedChoice = this.state.currentVotes[pollIndex];

        const voteData = {
            pollId: poll.id,
            choiceId: selectedChoice
        };
    }

    render() {
        const noteViews = [];
        this.state.notes.forEach((note, noteIndex) => {
            noteViews.push(<Poll
                key={note.id}
                note={note}
                handleVoteChange={(event) => this.handleNoteChange(event, noteIndex)}
                handleVoteSubmit={(event) => this.handleNoteSubmit(event, noteIndex)}/>)
        });

        return (
            <div className="note-container">
                {noteViews}
                {
                    !this.state.isLoading && this.state.polls.length === 0 ? (
                        <div className="no-note-found">
                            <span>No Notes Found.</span>
                        </div>
                    ) : null
                }
                {
                    !this.state.isLoading && !this.state.last ? (
                        <div className="load-more-notes">
                            <Button type="dashed" onClick={this.handleLoadMore} disabled={this.state.isLoading}>
                                <Icon type="plus"/> Load more
                            </Button>
                        </div>) : null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator/> : null
                }
            </div>
        );
    }
}

export default withRouter(NoteList);