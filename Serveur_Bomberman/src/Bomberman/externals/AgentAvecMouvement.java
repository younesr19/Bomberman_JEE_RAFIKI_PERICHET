package Bomberman.externals;


public class AgentAvecMouvement {
	private AgentBomberman agent;
	private AgentAction agentAction;
	public AgentAvecMouvement() {
		super();
	}
	public AgentAvecMouvement(AgentBomberman agent, AgentAction agentAction) {
		super();
		this.agent = agent;
		this.agentAction = agentAction;
	}
	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(AgentBomberman agent) {
		this.agent = agent;
	}
	/**
	 * @return the agentAction
	 */
	public AgentAction getAgentAction() {
		return agentAction;
	}
	/**
	 * @param agentAction the agentAction to set
	 */
	public void setAgentAction(AgentAction agentAction) {
		this.agentAction = agentAction;
	}
	
}
